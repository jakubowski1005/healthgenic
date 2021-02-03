import React, { useState } from "react";
import useForm from "./useForm";
import validate from "./validateInfo";
import Toogle from "../Toogle";
import "./Form.css";

const FormSignUp = ({ submitForm }) => {
  const { handleChange, handleSubmit, values, errors } = useForm(
    submitForm,
    validate
  );

  const [valueOn, setValueOn] = useState(false);

  let options = {
    PATIENT: "PATIENT",
    DOCTOR: "DOCTOR",
  };

  const [userInput, setUserInput] = useState("");

  const inputchangehandler = (event) => {
    setUserInput(event.target.value);
  };

  return (
    <div className="form-content-right">
      <form onSubmit={handleSubmit} className="form" noValidate>
        <h1>Get started with us today!</h1>
        <div className="form-inputs">
          <label className="form-label">Username</label>
          <input
            id="username"
            type="text"
            name="username"
            className="form-input"
            placeholder="Enter your username"
            value={values.username}
            onChange={handleChange}
          />
          {errors.username && <p>{errors.username}</p>}
        </div>
        <div className="form-inputs">
          <label className="form-label">First name</label>
          <input
            id="name"
            type="text"
            name="name"
            className="form-input"
            placeholder="Enter your first name"
            value={values.name}
            onChange={handleChange}
          />
          {errors.name && <p>{errors.name}</p>}
        </div>
        <div className="form-inputs">
          <label className="form-label">Last name</label>
          <input
            id="surename"
            type="text"
            name="surename"
            className="form-input"
            placeholder="Enter your last name"
            value={values.surename}
            onChange={handleChange}
          />
          {errors.surename && <p>{errors.surename}</p>}
        </div>
        <div className="form-inputs">
          <label htmlFor="email" className="form-label">
            Email
          </label>
          <input
            id="email"
            type="email"
            name="email"
            className="form-input"
            placeholder="Enter your email"
            value={values.email}
            onChange={handleChange}
          />
          {errors.email && <p>{errors.email}</p>}
        </div>
        <div className="form-inputs">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            id="password"
            type="password"
            name="password"
            className="form-input"
            placeholder="Enter your password"
            value={values.password}
            onChange={handleChange}
          />
          {errors.password && <p>{errors.password}</p>}
        </div>
        <div className="form-inputs">
          <label htmlFor="password2" className="form-label">
            Confirm Password
          </label>
          <input
            id="password2"
            type="password"
            name="password2"
            className="form-input"
            placeholder="Enter your password"
            value={values.password2}
            onChange={handleChange}
          />
          {errors.password2 && <p>{errors.password2}</p>}
        </div>

        <select name="role" value={values.role} onChange={handleChange}>
          {Object.keys(options).map(function (key) {
            return (
              <option key={key} value={key}>
                {options[key]}
              </option>
            );
          })}
        </select>

        <button className="form-input-btn" type="submit">
          Sign up
        </button>
        <span className="form-input-login">
          Already have an account? Login
          <a href="/login"> here</a>
        </span>
      </form>
    </div>
  );
};

export default FormSignUp;
