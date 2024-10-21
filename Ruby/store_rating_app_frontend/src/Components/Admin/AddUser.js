import React, { useEffect, useState } from "react";
import Navbar from "../Body/Navbar";
import { Link, useNavigate } from "react-router-dom";
import AxiosConfig from "../../AxiosConfig";

const AddUser = () => {
  const [registerUserData, setRegisterUserData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    role: "",
    password: "",
    address: "",
  });

  const [invalidName, setInvalidName] = useState(false);
  const [invalidEmail, setInvalidEmail] = useState(false);
  const [invalidPass, setInvalidPass] = useState(false);
  const [invalidAddress, setInvalidAddress] = useState(false);
  const [invalidRole, setInvalidRole] = useState(false);
  const navigate = useNavigate();

  const validateName = (e) => {
    if (/^[a-zA-Z ]{3,30}$/.test(e.target.value)) {
      setInvalidName(false);
      changeHandler(e);
    } else {
      setInvalidName(true);
    }
  };

  const validateEmail = (e) => {
    if (
      /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(e.target.value)
    ) {
      setInvalidEmail(false);
      changeHandler(e);
    } else {
      setInvalidEmail(true);
    }
  };

  const showPassword = (id) => {
    var x = document.getElementById(id);
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  };

  const validatePassword = (e) => {
    if (
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/.test(
        e.target.value
      )
    ) {
      setInvalidPass(false);
      changeHandler(e);
    } else {
      setInvalidPass(true);
    }
  };

  const validateAddress = (e) => {
    if (/^.{5,}$/.test(e.target.value)) {
      setInvalidAddress(false);
      changeHandler(e);
    } else {
      setInvalidAddress(true);
    }
  };

  const validateRole = (e) => {
    if (e.target.value !== "") {
      setInvalidRole(false);
      changeHandler(e);
    } else {
      setInvalidRole(true);
    }
  };

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setRegisterUserData({
      ...registerUserData,
      [name]: value,
    });
  };
  const submitRegister = async (event) => {
    event.preventDefault();
    if (
      invalidName ||
      invalidEmail ||
      invalidPass ||
      invalidAddress ||
      invalidRole
    ) {
      alert("invalid data filled!!!");
      // window.location.reload();
    } else {
      await AxiosConfig.post("/user/signup", registerUserData)
        .then((response) => {
          alert("New User Added Successful");
          console.log(response.data);
          //   localStorage.setItem("registeredEmail", registerUserData.email);
          //   localStorage.setItem("userId", response.data.id);
          navigate("/admin-home");
        })
        .catch((error) => {
          alert("provide valid details!!!");
        });
    }
  };
  return (
    <>
      <div className="page-container">
        <Navbar />
        <div className="heading">
          <h1>Add User</h1>
        </div>

        <form>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Name
            </label>
            <input
              type="text"
              name="name"
              className="form-control"
              id="name"
              aria-describedby=""
              required={true}
              onChange={(e) => (e ? validateName(e) : "")}
            />
            <div>
              {invalidName && (
                <>
                  <div>Invalid First Name format!!!</div>
                </>
              )}
            </div>
          </div>
          
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email address
            </label>
            <input
              type="email"
              name="email"
              className="form-control"
              id="email"
              placeholder="Please Enter Valid Email ID"
              aria-describedby="emailHelp"
              required={true}
              onChange={(e) => (e ? validateEmail(e) : "")}
            />

            <div>
              {invalidEmail && (
                <>
                  <div>Invalid Email format!!!</div>
                </>
              )}
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="col-form-label">
              Password
            </label>
            <input
              type="password"
              name="password"
              id="password"
              className="form-control"
              aria-describedby="passwordHelpInline"
              required={true}
              onChange={(e) => (e ? validatePassword(e) : "")}
            />
            <input
              className="m-1"
              type="checkbox"
              name=""
              id=""
              onClick={() => showPassword("password")}
            />
            <span>Show</span>
            <div>
              {invalidPass && (
                <>
                  <div>
                    Password(8-20 characters) Must Include Minimum 1
                    <ul>
                      <li>Uppercase Letter</li>
                      <li>Lowercase Letter</li>
                      <li>Special character</li>
                      <li>Number</li>
                    </ul>
                  </div>
                </>
              )}
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="address" className="form-label">
              address
            </label>
            <textarea
              className="form-control"
              id="address"
              rows="3"
              name="address"
              onChange={(e) => (e ? validateAddress(e) : "")}
            ></textarea>
            <div>
              {invalidAddress && (
                <>
                  <div>Minimum 5 charaters required!!!</div>
                </>
              )}
            </div>
          </div>

          <div className="">
            <label htmlFor="role" className="form-label">
              Role
            </label>
            <select
              name="role"
              className="form-select"
              id="role"
              required={true}
              onChange={(e) => (e ? validateRole(e) : "")}
            >
              <option selected disabled value="">
                -- Select role --
              </option>
              <option value="ROLE_SYSTEM_ADMIN">System Admin</option>
              <option value="ROLE_NORMAL_USER">Normal User</option>
              <option value="ROLE_STORE_OWNER">Store Owner</option>
            </select>
            <div>
              {invalidRole && (
                <>
                  <div>Invalid Role!!!</div>
                </>
              )}
            </div>
          </div>
          <br></br>

          <div style={{ marginLeft: "42%" }}>
            <button
              autoFocus
              className="cstmBtn"
              type="submit"
              onClick={submitRegister}
            >
              Add User
            </button>
          </div>
        </form>
      </div>
    </>
  );
};

export default AddUser;
