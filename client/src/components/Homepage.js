import React, { useState } from 'react'
import authService from '../services/authService'

function Homepage() {
  const user = authService.getCurrentUser()

  return (
    <div>
      <div className='container '>
        <header className='pb-2 px-3 pt-2 mb-4 border-bottom rounded-1'>
          <a
            href='/'
            className='d-flex align-items-center text-dark text-decoration-none'
          >
            <img
              src='https://1000logos.net/wp-content/uploads/2021/10/Batman-Logo-768x432.png'
              width='60'
              height='41'
              className='me-2'
              alt='ADP Logo'
            />
            <span className='fs-3 fw-bold'>Associate Expense Management System</span>
          </a>
        </header>

        <div className='p-3 mb-4 text-white bg-dark rounded-3'>
          <div className='container-fluid py-2'>
            <h1 className='display-6 fw-bold mb-5'>
              Welcome To Expense Management Section
            </h1>

            <p className='col-12 fs-5 mb-5'>
              Features :{' '}
              <span className='mark'>
                {' '}
                1. Employees Overview 2. Expense Overview 3. Approval/Rejection
                of Receipts{' '}
              </span>
            </p>

            <p className='col-12 fs-5 mb-4'>
              Login now to get your expenses approved.
            </p>

            <a href='/login'>
              <button className='btn btn-outline-light' type='button'>
                Login
              </button>
            </a>
          </div>
        </div>

        <div className='row align-items-md-stretch'>
          <div className='col-md-6'>
            <div className='h-100 p-5 text-white bg-dark rounded-3'>
              <h2>Adding an Expense</h2>
              <p className='m-4'>
                Creation of Expenses based on the category and other fields
              </p>

              <a href={user && user.id ? `/add-expense/${user.id}` : '/login'}>
                <button className='btn btn-outline-light' type='button'>
                  Add Expense
                </button>
              </a>
            </div>
          </div>
          <div className='col-md-6'>
            <div className='h-100 p-5 text-white bg-dark border rounded-3'>
              <h2>Manager Reports</h2>
              <p className='m-4'>
                Get list of all Employees and their Expense details along with
                the Expense Receipt
              </p>

              <a
                href={
                  user && user.id ? `manager-dashboard/${user.id}` : '/login'
                }
              >
                <button className='btn btn-outline-light' type='button'>
                  Manager Dashboard
                </button>
              </a>
            </div>
          </div>
          <div className='col-md-6 mt-4'>
            <div className='h-100 p-5 text-white bg-dark rounded-3'>
              <h2>Approvals and Rejections</h2>
              <p className='m-4'>
                Handling of approvals and rejections of expenses by Manager
                along with the Manager Comments.
              </p>

              <a
                href={
                  user && user.id ? `/expense/${user.id}` : '/login'
                }
              >
                <button className='btn btn-outline-light' type='button'>
                  View Expenses
                </button>
              </a>
            </div>
          </div>
          <div className='col-md-6 mt-4'>
            <div className='h-100 p-5 text-white bg-dark rounded-3'>
              <h2>Expense Visualizer</h2>
              <p className='m-4'>
                Dynamic Expense Details History for subordinates of each Manager
              </p>

              <a
                href={
                  user && user.id ? `manager-dashboard/${user.id}` : '/login'
                }
              >
                <button className='btn btn-outline-light' type='button'>
                  Visualizer
                </button>
              </a>
            </div>
          </div>
        </div>

        <footer className='pt-3 mt-4 text-muted border-top'>&copy; Made by <b>DD</b></footer>
      </div>
    </div>
  )
}

export default Homepage
