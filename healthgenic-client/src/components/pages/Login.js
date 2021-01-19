import React, { useState } from "react";
import FormLogin from "./formSign/FormLogin";
import Doctor from "../../doctor/Doctor";
import Footer from "../Footer";
import "../../App.css";
import "./formSign/Form.css";
import Navbar from "../Navbar";

const Login = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);

  function submitForm() {
    setIsSubmitted(true);
  }

  return (
    <>
      {!isSubmitted ? (
        <React.Fragment>
          <Navbar />
          <div className="form-container2">
            <div className="form-content-left">
              <img
                className="form-img"
                alt="LOGIN"
                src="images/img-medical.jpg"
              />
            </div>
            <FormLogin submitForm={submitForm} />
          </div>
          <Footer />
        </React.Fragment>
      ) : (
        <Doctor />
      )}
    </>
  );
};

export default Login;
