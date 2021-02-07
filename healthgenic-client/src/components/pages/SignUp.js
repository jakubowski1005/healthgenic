import React, { useState } from "react";
import FormSignUp from "./formSign/FormSignUp";
import Login from "./Login";
import Footer from "../Footer";
import "../../App.css";
import "./formSign/Form.css";
import Navbar from "../Navbar";

const SignUp = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);
  function submitForm() {
    setIsSubmitted(true);
  }

  return (
    <>
      {!isSubmitted ? (
        <React.Fragment>
          <Navbar />
          <div className="form-container">
            <div className="form-content-left">
              <img
                className="form-img"
                alt="SING UP"
                src="images/img-med.jpg"
              />
            </div>
            <FormSignUp submitForm={submitForm} />
          </div>
          <Footer />
        </React.Fragment>
      ) : (
        <Login />
      )}
    </>
  );
};

export default SignUp;
