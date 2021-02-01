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

function App() {
  return (
    <>
      <Router>
        <ScrollToTop />
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/login" component={Login} />
          <Route path="/sign-up" component={SignUp} />
          <Route path="/doctor" component={Doctor} />
          <Route path="/patientList" component={PatientList} />
          <Route path="/messagesD" component={MessageD} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
