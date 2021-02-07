import React from "react";
import "./App.css";
import Home from "./components/pages/Home";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Login from "./components/pages/Login";
import SignUp from "./components/pages/SignUp";
import Doctor from "./doctor/Doctor";
import PatientList from "./doctor/PatientList";
import MessageD from "./doctor/message/Message";
import ScrollToTop from "./components/ScrollToTop";
import AuthenticatedRoute from "./components/authentication/AuthenticatedRoute";

function App() {
  return (
    <>
      <Router>
        <ScrollToTop />
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/login" component={Login} />
          <Route path="/sign-up" component={SignUp} />
          <AuthenticatedRoute path="/doctor" component={Doctor} />
          <AuthenticatedRoute path="/patientList" component={PatientList} />
          <AuthenticatedRoute path="/messagesD" component={MessageD} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
