import { useState, useEffect } from "react";
import AuthService from "../../../services/AuthService";

const useForm = (callback, validate) => {
  const [values, setValues] = useState({
    username: "",
    name: "",
    surename: "",
    email: "",
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
      AuthService.register(values); // !!!!!!!!!! AUTHSERVICE !!!!!!!!!!!!!!
    }
  }, [errors]);

  return { handleChange, handleSubmit, values, errors };
};

export default useForm;
