@extends('firebase.app')

@section('content')

<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4>Resident Record
                        <a href="{{ url('MainPatient') }}" class="btn btn-sm btn-danger float-end">BACK</a>
                    </h4>
                </div>
                <div class="card-body">

                    <form action="{{ url('add-resident') }}" method="POST">
                        @csrf

                        <div class="form-group mb-1">
                            <label>Date of Consultation</label>
                            <input type="text" name="date" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Name</label>
                            <input type="text" name="name" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Age</label>
                            <input type="text" name="age" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Gender</label>
                            <input type="text" name="gender" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Birthdate</label>
                            <input type="text" name="birthdate" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Address</label>
                            <input type="text" name="address" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Chief Complaint</label>
                            <input type="text" name="chiefcomplaint" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Diagnosis</label>
                            <input type="text" name="diagnosis" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Treatment</label>
                            <input type="text" name="treatment" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

@endsection
