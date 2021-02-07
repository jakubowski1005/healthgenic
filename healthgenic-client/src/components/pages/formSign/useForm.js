import { useState, useEffect } from "react";
import { register, login, logout } from "../../../services/AuthService";

const useForm = (callback, validate) => {
  const [values, setValues] = useState({
    username: "",
    name: "",
    surname: "",
    email: "",
    role: "PATIENT",
    password: "",
    password2: "",
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
      console.log(values.role);
      register(values); // !!!!!!!!!! AUTHSERVICE !!!!!!!!!!!!!!
    }
  }, [errors]);

  return { handleChange, handleSubmit, values, errors };
};

export default useForm;
