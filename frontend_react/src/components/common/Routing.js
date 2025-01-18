// src/Components/Common/Routing.js
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap.min.css";
import App from "../../App.js";
import { Route, Switch } from "react-router-dom/cjs/react-router-dom.min";
import Home from "../../pages/Home.js";
import Competition from "../../pages/Competition.js";
import Login from "../user/Login.js";
import Join from "../user/Join.js";
import StudyBoard from "../../pages/StudyBoard.js";
function Routing() {
  return (
    <Switch>
      <Route path="/" exact component={App} />
      <Route path="/home" exact component={Home} />
      <Route path="/competition" exact component={Competition} />
      <Route path="/login" exact component={Login}></Route>
      <Route path="/join" exact component={Join} />
      <Route path="/studyBoard" exact component={StudyBoard}></Route>
    </Switch>
  );
}

export default Routing;
