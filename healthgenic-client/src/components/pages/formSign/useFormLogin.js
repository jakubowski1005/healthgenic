import { useState, useEffect } from "react";
import { register, login, logout } from "../../../services/AuthService";
import userD from "../../../doctor/jsons/userD.json";

const useFormLogin = (callback, validate) => {
  const [values, setValues] = useState({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setValues({
      ...values,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    setErrors(validate(values));
    setIsSubmitting(true);
  };

  useEffect(() => {
    if (Object.keys(errors).length === 0 && isSubmitting) {
      login(values)
          .then(response => response.json())
          .then(data => {
              sessionStorage.setItem("token", data.token);
              window.location.assign("/");
          })
          .catch(err => console.error(err));
    }
  }, [errors]);

  return { handleChange, handleSubmit, values, errors };
};

export default useFormLogin;
