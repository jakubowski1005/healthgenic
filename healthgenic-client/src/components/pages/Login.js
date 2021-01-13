import React, { useState } from "react";
import FormLogin from "./formSign/FormLogin";
import Doctor from "../../doctor/Doctor";
import Footer from "../Footer";
import "../../App.css";

const Login = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);

  function submitForm() {
    setIsSubmitted(true);
  }

  return (
    <>
      <div className="form-container2">
        <div className="form-content-left">
          <img className="form-img" src="images/img-medical.jpg" />
        </div>
        {!isSubmitted ? <FormLogin submitForm={submitForm} /> : <Doctor />}
      </div>

      <Footer />
    </>
  );
};

export default Login;
