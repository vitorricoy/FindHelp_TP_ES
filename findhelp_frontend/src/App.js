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

import UsuarioService from "./services/UsuarioService";

export default function App() {
  window.addEventListener('beforeunload', (event) => {
    event.preventDefault();
    new UsuarioService().deslogarUsuario();
    event.returnValue = ''
    localStorage.removeItem('idUsuario');
  });
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
