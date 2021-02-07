import React, { Component } from "react";
import ".././components/pages/formSign/Form.css";
import "./ProfileD.css";
import { getUserInfo } from "../services/UserService";

export default class ProfileDD extends Component {
  constructor(props) {
    super(props);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangeSurname = this.onChangeSurname.bind(this);
    this.getDoctor = this.getDoctor.bind(this);

    this.state = {
      currentDoctor: {
        doctorId: null,
        userData: {
          name: "",
          surname: "",
          age: "",
        },
        email: "",
        username: "",
      },
      message: "",
    };
  }

  componentDidMount() {
    this.getDoctor();
  }

  onChangeName(e) {
    const name = e.target.value;

    this.setState(function (prevState) {
      return {
        currentDoctor: {
          ...prevState.currentDoctor,
          name: name,
        },
      };
    });
  }

  onChangeSurname(e) {
    const surname = e.target.value;

    this.setState((prevState) => ({
      currentDoctor: {
        ...prevState.currentDoctor,
        surname: surname,
      },
    }));
  }

  getDoctor() {
    getUserInfo()
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          currentDoctor: data,
        });
        console.log(data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const { currentDoctor } = this.state;

    const User = ({ firstName, lastName, email, username }) => {
      if (!firstName) return <div />;
      return (
        <>
          <formm>
            <div class="row1">
              <label for="i1" class="col1">
                Name :
              </label>
              <input id="i1" class="col1" type="text" value={firstName} />

              <label for="i2" class="col2">
                Surname :
              </label>
              <input id="i2" class="col2" type="text" value={lastName} />
            </div>
            <div class="row2">
              <label for="i3" class="col1">
                Email :
              </label>
              <label id="i3" class="col1">
                {email}
              </label>

              <label for="i4" class="col2">
                Username :
              </label>
              <label id="i4" class="col2">
                {username}
              </label>
            </div>
          </formm>
        </>
      );
    };

    return (
      <>
        <div className="form-container-d">
          <div className="form-content-left-d"></div>
          <div className="form-content-right-d">
            <User
              firstName={currentDoctor.userData.name}
              lastName={currentDoctor.userData.surname}
              email={currentDoctor.email}
              username={currentDoctor.username}
            />
          </div>
        </div>
      </>
    );
  }
}

/* <div>
        <div className="edit-form">
          <h4>Teacher</h4>
          <form>
            <div className="form-group">
              <label htmlFor="language">language</label>
              <input
                type="text"
                className="form-control"
                id="language"
                value={currentDoctor.email}
                onChange={this.onChangeLanguage}
              />
            </div>

            <div className="form-group">
              <label htmlFor="name">name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={currentDoctor.userData.name}
                onChange={this.onChangeName}
              />
            </div>
            <div className="form-group">
              <label htmlFor="surname">surname</label>
              <input
                type="text"
                className="form-control"
                id="surname"
                value={currentDoctor.userData.surname}
                onChange={this.onChangeSurname}
              />
            </div>
          </form>
        </div>
      </div>
      */
