import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { createUser, checkUserId } from "../../apitmp/UserApi";
const Join = (props) => {
  const [user, setUser] = useState({
    id: "",
    password: "",
    email: "",
    name: "",
  });

  const [isUserIdValid, setIsUserIdValid] = useState(false);

  const changeValue = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const checkUser = async (userId) => {
    try {
      const response = await checkUserId(userId);
      if (response.status === 200) {
        setIsUserIdValid(true);
        alert(response.data);
      } else {
        alert(response.data);
      }
    } catch (error) {
      alert(error.response.data);
    }
  };

  const joinUser = async (e) => {
    e.preventDefault();
    console.log("회원가입!!", isUserIdValid);
    if (!isUserIdValid) {
      alert("아이디 중복체크를 해주세요.");
      console.log("중복체크!!");
      return;
    }
    try {
      const userData = {
        userId: user.id,
        userPassword: user.password,
        userName: user.name || "홍길동",
        userEmail: user.email || "default@example.com",
      };
      console.log(userData);
      await createUser(userData);
      // props.history.push("/");
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
          <Button
            variant="outline-secondary"
            onClick={() => checkUser(user.id)}
          >
            아이디 중복 체크
          </Button>
          {/* {isUserIdValid && <p style={{color : "red"}}>이미 존재하는 아이디 입니다.</p>} */}
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
