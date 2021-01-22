import React from "react";
import ".././components/pages/formSign/Form.css";
import "./ProfileD.css";
import userData from "./jsons/userD.json";

const ProfileD = () => {
  return (
    <>
      <div className="form-container-d">
        <div className="form-content-left-d"></div>
        <div className="form-content-right-d">
          {userData.map((data, key) => {
            return (
              <div key={key}>
                <User
                  key={key}
                  firstName={data.firstName}
                  lastName={data.lastName}
                  email={data.email}
                  username={data.username}
                />
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
};

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
export default ProfileD;
