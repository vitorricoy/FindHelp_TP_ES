import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

export default function App() {
  return (
      <Router>
        <div>
          <Switch>
            <Route path="/chat">
              <Chat />
            </Route>
            <Route path="/">
              <Login />
            </Route>
          </Switch>
        </div>
      </Router>
  );
}

function Login() {
  return <h2>Login</h2>;
}

function Chat() {
  return <h2>Chat</h2>;
}
