import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { checkLoginUser } from "../../apitmp/UserApi";
const Login = (props) => {
  const [user, setUser] = useState({
    id: "",
    password: "",
  });

  const changeValue = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const loginUser = async (e) => {
    e.preventDefault();
    try {
      console.log("로그인정보 : ", user.id, user.password);
      const response = await checkLoginUser(user.id, user.password);
      const token = response.headers["authorization"].replace("Bearer ", "");
      console.log("로그인성공", token);
      localStorage.setItem("token", token);
      props.history.push("/studyBoard");
    } catch (error) {
      console.log("로그인 실패 : ", error);
    }
  };

  return (
    <>
      <h1>로그인 페이지</h1>
      <Form onSubmit={loginUser}>
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

        {/* 로그인 버튼 */}
        <div className="d-grid">
          <Button variant="primary" type="submit">
            로그인
          </Button>
        </div>
      </Form>
    </>
  );
};

export default Login;
