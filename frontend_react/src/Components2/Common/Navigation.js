// src/Components/Common/Navigation.js
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import "../../App.css";

function Navigation() {
  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="/">Share Mate</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="home">홈</Nav.Link>
              <Nav.Link href="#">방 만들기</Nav.Link>
              <Nav.Link href="competition">공모전</Nav.Link>
              <Nav.Link href="login">로그인</Nav.Link>
              <Nav.Link href="join">회원가입</Nav.Link>
              <NavDropdown title="마이페이지" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.1">
                  내 프로필
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">
                  내 채팅방
                </NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">내 글</NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">DM</NavDropdown.Item>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  );
}

export default Navigation;
