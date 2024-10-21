import React, { useEffect, useState } from "react";
import AxiosConfig from "../../AxiosConfig";
import { Link, useNavigate } from "react-router-dom";

const ChangePassword = () => {
  const [newPassword, setNewPassword] = useState('');
  const [invalidPass, setInvalidPass] = useState(false);
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const validatePassword = (e) => {
    const password = e.target.value;
    setNewPassword(password);

    if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/.test(password)) {
      setInvalidPass(false);
    } else {
      setInvalidPass(true);
    }
  };

  const showPassword = (id) => {
    var x = document.getElementById(id);
    if (x.type === 'password') {
      x.type = 'text';
    } else {
      x.type = 'password';
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (invalidPass) {
      alert('Invalid password format!');
      return;
    }

    const userId = JSON.parse(localStorage.getItem('loggedInUserId')); 

    if (!userId) {
      setMessage('User not logged in.');
      return;
    }

    try {
      const response = await AxiosConfig.post(`user/changepassword/${newPassword}`, userId);
      setMessage('Password changed successfully!');
      console.log(response.data);
      navigate('/'); 
    } catch (error) {
      console.error('Error changing password:', error);
      setMessage('Failed to change password. Please try again.');
    }
  };

  return (
    <div
      className="page-container"
      style={{ marginTop: 100, marginLeft: 200, marginRight: 200 }}
    >
      <div className="heading">
        <h1>Change Password</h1>
      </div>

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="newPassword" className="form-label">New Password</label>
          <input
            type="password"
            name="newPassword"
            id="newPassword"
            className="form-control"
            required={true}
            value={newPassword}
            onChange={validatePassword}
          />
          <input
            type="checkbox"
            className="m-1"
            onClick={() => showPassword('newPassword')}
          /> Show Password
          <div>
            {invalidPass && (
              <div>
                Password must include:
                <ul>
                  <li>8-20 characters</li>
                  <li>1 Uppercase letter</li>
                  <li>1 Lowercase letter</li>
                  <li>1 Number</li>
                  <li>1 Special character (@$!%*?&)</li>
                </ul>
              </div>
            )}
          </div>
        </div>

        <button type="submit" className="btn btn-primary">
          Change Password
        </button>
      </form>

      {message && <p>{message}</p>} 
    </div>
  );
};

export default ChangePassword;
