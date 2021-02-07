import { useState, useEffect } from "react";
import { login } from "../../../services/AuthService";
import { getUserInfo } from "../../../services/UserService";

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
      sessionStorage.clear();
      login(values)
        .then((response) => response.json())
        .then((data) => {
          sessionStorage.setItem("token", data.token);
          console.log(data);

          if (sessionStorage.getItem("token")) {
            getUserInfo()
              .then((response) => response.json())
              .then((data1) => {
                console.log(data1);
                if (data1.roles[0] == "DOCTOR") {
                  window.location.assign("/patientList");
                  return false;
                } else {
                  window.location.assign("/");
                }
              })
              .catch((e) => {
                console.log(e);
              });
          }
        })
        .catch((err) => console.error(err));
    }
  }, [errors]);

  return { handleChange, handleSubmit, values, errors };
};

export default useFormLogin;
