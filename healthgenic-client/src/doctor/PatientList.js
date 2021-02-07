import React, { Component } from "react";
import NavbarD from "./NavbarD";
import MeasurementList from "./messurments/MeasurementList";
//SERWIS z LISTĄ PACJENTÓW !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import "./List.css";
import { getRelatedUsers } from "../services/UserService";

export default class ProfileDD extends Component {
  constructor(props) {
    super(props);
    this.retrievepatients = this.retrievepatients.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActivePatient = this.setActivePatient.bind(this);

    this.state = {
      patients: [],
      currentPatient: null,
      currentIndex: -1,
    };
  }

  componentDidMount() {
    this.retrievepatients();
  }

  retrievepatients() {
    getRelatedUsers()
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          patients: data,
        });
        console.log(data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrievepatients();
    this.setState({
      currentPatient: null,
      currentIndex: -1,
    });
  }

  setActivePatient(patient, index) {
    console.log(patient);
    this.setState({
      currentPatient: patient,
      currentIndex: index,
    });
  }

  render() {
    const { patients, currentPatient, currentIndex } = this.state;

    const Patients = ({ patientid, firstName, lastName, patient, index }) => {
      if (!firstName) return <div />;
      return (
        <>
          <table id="table-names">
            <tbody>
              <tr onClick={() => this.setActivePatient(patient, index)}>
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

    return (
      <>
        <NavbarD />
        <div className="form-container-m">
          <div className="form-content-left-l">
            {patients.map((patient, index) => {
              return (
                <div index={index}>
                  <Patients
                    patientid={patient.id}
                    firstName={patient.name}
                    lastName={patient.surname}
                    patient={patient}
                    index={index}
                  />
                </div>
              );
            })}
          </div>

          <div className="form-content-right-m">
            {!currentPatient ? (
              ""
            ) : (
              <MeasurementList PATIENT_USER_ID={currentPatient.id} />
            )}
          </div>
        </div>
      </>
    );
  }
}

/*
<>
        <NavbarD />
        <div className="form-container-teach">
          <div className="form-content-left-teach">
            <div className="form-content-left-bottom-teach">
              <ul className="list-group">
                {patients &&
                  patients.map((patient, index) => (
                    <li
                      className={
                        "list-group-item " +
                        (index === currentIndex ? "active" : "")
                      }
                      onClick={() => this.setActivePatient(patient, index)}
                      key={index}
                    >
                      {patient.language} - {patient.name} {patient.surename}
                    </li>
                  ))}
              </ul>
            </div>
          </div>

          <div className="form-content-right-teach">
            <div className="form-teach">
              {currentPatient ? (
                <div>
                  <h4>
                    {currentPatient.name} {"  "} {currentPatient.surname}
                  </h4>

                  {console.log(currentPatient.patientId)}
                </div>
              ) : (
                <div>
                  <br />
                  <p>Please click on a patient...</p>
                </div>
              )}
            </div>
          </div>
        </div>
      </>
*/

/*
    return (
      <>
        <NavbarD />
        <div className="form-container-m">
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

          <div className="form-content-right-m">
            <MeasurementList PATIENT_USER_ID={state} />
          </div>
        </div>
      </>
    );
  }
}
*/
/*
export const PatientList = () => {
  const [state, setState] = useState("");

  const Patients = ({ patientid, firstName, lastName }) => {
    if (!firstName) return <div />;
    return (
      <>
        <table id="table-names">
          <tbody>
            <tr onClick={() => setState(patientid)}>
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

  return (
    <>
      <NavbarD />
      <div className="form-container-m">
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

        <div className="form-content-right-m">
          <MeasurementList PATIENT_USER_ID={state} />
        </div>
      </div>
    </>
  );
};

export default PatientList;
*/
