import React, { useState } from "react";
import NavbarD from "./NavbarD";
import patientData from "./jsons/patientList.json";
import "./List.css";
import MeasurementList from "./messurments/MeasurementList";

export const PatientList = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);

  function submitForm() {
    setIsSubmitted(true);
  }

  return (
    <>
      <NavbarD />
      <div className="form-container-l">
        <div className="form-content-left-l">
          {patientData.map((data, key) => {
            return (
              <div key={key}>
                <Patients
                  key={key}
                  patientid={data.id}
                  firstName={data.firstName}
                  lastName={data.lastName}
                />
              </div>
            );
          })}
        </div>
        <div className="form-content-right-l">
          {!isSubmitted ? (
            <MeasurementList submitForm={submitForm} />
          ) : (
            <MeasurementList />
          )}
        </div>
      </div>
    </>
  );
};

const Patients = ({ patientid, firstName, lastName }) => {
  if (!firstName) return <div />;
  return (
    <>
      <table id="table-names">
        <tbody>
          <tr data-user={patientid}>
            <td>
              <h5>
                {firstName} {lastName}
              </h5>
            </td>
          </tr>
        </tbody>
      </table>
    </>
  );
};

document.addEventListener("DOMContentLoaded", () => {
  const rows = document.querySelectorAll("tr[data-user");
  console.log(rows);
  let p_id = "";

  rows.forEach((row) => {
    row.addEventListener("click", () => {
      console.log(row.dataset.user);
      p_id = row.dataset.user;
    });
  });
});

export default PatientList;
