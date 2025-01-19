import { Button, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import CompList from "../components/competitiontmp/CompList";

function Competition() {
  return (
    <>
      <h2
        style={{
          padding: 10,
          display: "flex",
          justifyContent: "space-between",
        }}
      >
        [공모전] 게시판
        <Button variant="primary">
          <Nav.Link as={Link} to="/competition-form">
            게시글 추가
          </Nav.Link>
        </Button>
      </h2>
      <br></br>
      <CompList />
    </>
  );
}

export default Competition;
