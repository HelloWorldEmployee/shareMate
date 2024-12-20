import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { createUser } from "../../Api/userApi";
const Join = (props) => {
  const [user, setUser] = useState({
    id: "",
    password: "",
    email: "",
    name: "",
  });

  const changeValue = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const joinUser = async (e) => {
    e.preventDefault();
    try {
      const userData = {
        user_id: user.id,
        user_password: user.password,
        user_name: user.name || "홍길동",
        user_email: user.email || "default@example.com",
      };
      console.log(userData);
      await createUser(userData);
      props.history.push("/");
    } catch (error) {
      console.log(user);
      console.log(error);
      console.log("회원가입 실패!!");
    }
  };
  return (
    <>
      <h1>회원가입 페이지</h1>
      <Form onSubmit={joinUser}>
        {/* 아이디입력 */}
        <Form.Group className="mb-3" controlId="formId">
          <Form.Label>아이디</Form.Label>
          <Form.Control
            type="text"
            name="id"
            value={user.id}
            onChange={changeValue}
            required
          />
        </Form.Group>

        {/* 비밀번호 입력 */}
        <Form.Group className="mb-3" controlId="formPassword">
          <Form.Label>비밀번호</Form.Label>
          <Form.Control
            type="password"
            placeholder="비밀번호를 입력하세요"
            name="password"
            value={user.password}
            onChange={changeValue}
            required
          />
          <Form.Text className="text-muted">
            비밀번호는 8-20자의 문자와 숫자를 포함해야 합니다.
          </Form.Text>
        </Form.Group>

        {/* 이름입력 */}
        <Form.Group className="mb-3" controlId="formName">
          <Form.Label>이름</Form.Label>
          <Form.Control
            type="text"
            placeholder="홍길동"
            name="name"
            value={user.name}
            onChange={changeValue}
          />
        </Form.Group>

        {/* 이메일 입력 */}
        <Form.Group className="mb-3" controlId="formEmail">
          <Form.Label>이메일 주소</Form.Label>
          <Form.Control
            type="email"
            placeholder="default@example.com"
            name="email"
            value={user.email}
            onChange={changeValue}
          />
        </Form.Group>

        {/* 회원가입 버튼 */}
        <div className="d-grid">
          <Button variant="primary" type="submit">
            회원가입
          </Button>
        </div>
      </Form>
    </>
  );
};

export default Join;
