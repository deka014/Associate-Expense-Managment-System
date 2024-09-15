import React from 'react'
import { useParams } from 'react-router-dom'

export default function EmployeeDashboard() {
  const { id } = useParams()

  const handleAllExpenses = (event) => {
    event.preventDefault()
    window.location.href = `/expense/${id}`
  }

  const handleApprovedExpenses = (event) => {
    event.preventDefault()
    window.location.href = `/expense/${id}/status/approved`
  }

  const handlePendingExpenses = (event) => {
    event.preventDefault()
    window.location.href = `/expense/${id}/status/pending`
  }

  const handleRejectedExpenses = (event) => {
    event.preventDefault()
    window.location.href = `/expense/${id}/status/rejected`
  }

  const handleDraftExpenses = (event) => {
    event.preventDefault()
    window.location.href = `/drafts/${id}`
  }

  return (
    <div className='container mb-5'>
      <div>
      <div class='lc-block mb-5'>
        <h2>
          Employee <span className='mark'>Dashboard</span>
        </h2>
      </div>
        <a
          name=''
          id=''
          className='btn btn-warning'
          href={`/add-expense/` + id}
          role='button'
        >
          Create New Expense
        </a>
      </div>

      <div className='row mt-3'>
        <div className='col'>
          <div className='card'>
            <div className='card-body'>
              <h5 className='card-title'>All Expenses</h5>
              <p className='card-text'>View All Expenses</p>
              <button onClick={handleAllExpenses} className='btn btn-primary'>
                Click Here
              </button>
            </div>
          </div>
        </div>
        <div className='col'>
          <div className='card'>
            <div className='card-body'>
              <h5 className='card-title'>Approved Expenses</h5>
              <p className='card-text'>View All Approved Expenses</p>
              <button
                onClick={handleApprovedExpenses}
                className='btn btn-primary'
              >
                Click Here
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className='row mt-3'>
        <div className='col'>
          <div className='card'>
            <div className='card-body'>
              <h5 className='card-title'>Pending Expenses</h5>
              <p className='card-text'>View All Pending Expenses</p>
              <button
                onClick={handlePendingExpenses}
                className='btn btn-primary'
              >
                Click Here
              </button>
            </div>
          </div>
        </div>
        <div className='col'>
          <div className='card'>
            <div className='card-body'>
              <h5 className='card-title'>Rejected Expenses</h5>
              <p className='card-text'>View All Rejected Expenses</p>
              <button
                onClick={handleRejectedExpenses}
                className='btn btn-primary'
              >
                Click Here
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className='row mt-3'>
        <div className='col'>
          <div className='card'>
            <div className='card-body'>
              <h5 className='card-title'>Draft Expenses</h5>
              <p className='card-text'>View All Draft Expenses</p>
              <button onClick={handleDraftExpenses} className='btn btn-primary'>
                Click Here
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
