// src/components/common/Routing.js
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap.min.css";
import App from "../../App.js";
import { Route, Switch } from "react-router-dom/cjs/react-router-dom.min";
import Home from "../../pages/Home.js";
import Join from "../user/Join.js";
import Login from "../user/Login.js";
import CompForm from "../competition/CompForm.js";
import Competition from "../../pages/Competition.js";
import CompDetail from "../competition/CompDetail.js";
import StudyBoard from "../../pages/StudyBoard.js";
import CompUpdateForm from "../competition/CompUpdateForm.js";

function Routing() {
  return (
    <Switch>
      <Route path="/" exact component={App} />
      <Route path="/home" exact component={Home} />
      <Route path="/competition" exact component={Competition} />
      <Route path="/competition-form" component={CompForm} />
      <Route path="/competition/:compId" component={CompDetail} />
      <Route
        path="/competition-updateForm/:compId"
        component={CompUpdateForm}
      />
      <Route path="/login" exact component={Login}></Route>
      <Route path="/join" exact component={Join} />
      <Route path="/studyBoard" exact component={StudyBoard}></Route>
    </Switch>
  );
}

export default Routing;
