import React, { Component } from "react";
import NavbarD from "../NavbarD";
import MessageForm from "./MessageForm";
import "./Message.css";
import { getRelatedUsers } from "../../services/UserService";

export default class Message extends Component {
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
              <MessageForm PATIENT_USER_ID={currentPatient.id} />
            )}
          </div>
        </div>
      </>
    );
  }
}
