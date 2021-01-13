import React, { useState } from "react";
import FormSignUp from "./formSign/FormSignUp";
import Login from "./Login";
import Footer from "../Footer";
import "../../App.css";

const SignUp = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);

  function submitForm() {
    setIsSubmitted(true);
  }

  return (
    <>
      <div className="form-container">
        <div className="form-content-left">
          <img className="form-img" src="images/img-med.jpg" />
        </div>
        {!isSubmitted ? <FormSignUp submitForm={submitForm} /> : <Login />}
      </div>

      <Footer />
    </>
  );
};

export default SignUp;
