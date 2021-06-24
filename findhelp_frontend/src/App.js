import React from "react";
import Chat from './pages/chat/Chat'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Login from './pages/login/Login'
import Cadastro from './pages/cadastro/Cadastro'
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

export default function App() {
  return (
      <Router>
          <Switch>
            <Route path="/chat">
              <Chat />
            </Route>
            <Route path="/cadastro">
              <Cadastro />
            </Route>
            <Route path="/">
              <Login />
            </Route>
          </Switch>
      </Router>
  );
}
