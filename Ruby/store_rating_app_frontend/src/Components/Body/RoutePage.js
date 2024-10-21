import React from 'react'
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LogIn from "./LogIn"
import RegistrationPg from './RegistrationPg';
import AdminHome from '../Admin/AdminHome';
import OwnerHome from '../StoreOwner/OwnerHome';
import UserHome from '../NormalUser/UserHome';
import ChangePassword from './ChangePassword';
import AddUser from '../Admin/AddUser';
import StoresList from '../Admin/StoresList';
import AddStore from '../Admin/AddStore';

const RoutePage = () => {
  return (
    <>
    <BrowserRouter>
    <Routes>
        <Route path="/" element ={< LogIn />} />
        <Route path="/registration" element = {<RegistrationPg />} />
        <Route path="/admin-home" element = {<AdminHome />} />
        <Route path="/user-home" element = {<UserHome />} />
        <Route path="/store-owner-home" element = {< OwnerHome />} />
        <Route path="/change-password" element = {< ChangePassword />} />
        <Route path="/add-user" element = {<AddUser />} />
        <Route path="/add-store" element = {<AddStore />} />
        <Route path="/stores-list" element = {<StoresList />} />
        
    </Routes>
    </BrowserRouter>
    
    </>
  )
}

export default RoutePage