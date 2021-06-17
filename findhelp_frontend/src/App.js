import React from "react";
import Chat from './pages/chat/Chat'
import {
  BrowserRouter as Router,
  Switch,
  Route
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
