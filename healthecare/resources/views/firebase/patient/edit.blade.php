@extends('firebase.app')

@section('content')

<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4> Edit Resident Record
                        <a href="{{ url('MainPatient') }}" class="btn btn-sm btn-danger float-end">BACK</a>
                    </h4>
                </div>
                <div class="card-body">

                    <form action="{{ url('update-resident/' .$key) }}" method="POST">
                        @csrf
                        @method('PUT')


                        <div class="form-group mb-1">
                            <label>Date of Consultation</label>
                            <input type="text" name="date" value="{{ $editdata['date'] }}"  class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Name</label>
                            <input type="text" name="name"  value="{{ $editdata['name'] }}"  class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Age</label>
                            <input type="text" name="age"  value="{{ $editdata['age'] }}"  class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Gender</label>
                            <input type="text" name="gender"  value="{{ $editdata['gender'] }}"  class="form-control">t
                        </div>

                        {{--  <div class="form-group mb-3">
                            <label>Birthdate</label>
                            <input type="text" name="birthdate"  value="{{ $editdata['birthdate'] }}" class="form-control">
                        </div>  --}}

                        <div class="form-group mb-3">
                            <label>Address</label>
                            <input type="text" name="address"  value="{{ $editdata['address'] }}"  class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Chief Complaint</label>
                            <input type="text" name="chiefcomplaint"  value="{{ $editdata['chiefcomplaint'] }}" class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Diagnosis</label>
                            <input type="text" name="diagnosis"  value="{{ $editdata['diagnosis'] }}"  class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <label>Treatment</label>
                            <input type="text" name="treatment"  value="{{ $editdata['treatment'] }}"  class="form-control">
                        </div>

                        <div class="form-group mb-3">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

@endsection
