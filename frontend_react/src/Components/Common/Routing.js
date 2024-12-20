// src/Components/Common/Routing.js
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap.min.css";
import App from "../../App.js";
import { Route, Switch } from "react-router-dom/cjs/react-router-dom.min";
import Home from "../../Pages/Home.js";
import Competition from "../../Pages/Competition.js";
import Login from "../User/Login.js";
import Join from "../../Pages/user/Join.js";
function Routing() {
  return (
    <Switch>
      <Route path="/" exact component={App} />
      <Route path="/home" exact component={Home} />
      <Route path="/competition" exact component={Competition} />
      <Route path="/login" exact component={Login}></Route>
      <Route path="/join" exact component={Join} />
    </Switch>
  );
}

export default Routing;
