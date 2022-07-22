<?php

namespace App\Http\Controllers\Firebase;

use App\Http\Controllers\Controller;
use Google\Cloud\Storage\Connection\Rest;
use Illuminate\Http\Request;
use Kreait\Firebase\Contract\Database;

class ContactController extends Controller
{
    public function __construct(Database $database)
    {
        $this->database = $database;
        $this->tableName = 'MainPatient';
    }

    public function index()
    {
        $MainPatient = $this->database->getReference($this->tableName)->getValue();
        return view('firebase.patient.index' , compact('MainPatient'));
    }

    public function create()
    {
        return view('firebase.patient.create');
    }

    public function store(Request $request)
    {
        $postData = [
            'date' => $request->date,
            'name' => $request->name,
            'age' => $request->age,
            'gender' => $request->gender,
            'birthdate' => $request->birthdate,
            'address' => $request->address,
            'chiefcomplaint' => $request->chiefcomplaint,
            'diagnosis' => $request->diagnosis,
            'treatment' => $request->treatment,

        ];
        $postRef = $this->database->getReference($this->tableName)->push($postData);
        if($postRef)
        {
            return redirect('MainPatient')->with('status', 'Successfully Added Resident Record');
        }
        else
        {
            return redirect('MainPatient')->with('status', 'Resident Not Added');
        }
    }

    public function edit ($id)
    {
        $key = $id;
        $editdata =$this->database ->getReference ($this->tableName)->getChild($key)->getValue();
        if($editdata)
        {
            return view('firebase.patient.edit',compact('editdata','key'));
        }

        {
            return redirect('MainPatient')->with('status', 'Resident ID Not Found');
        }
    }

    public function update (Request $request, $id )

    {
        $key = $id;
        $updateData = [
            'date' => $request->date,
            'name' => $request->name,
            'age' => $request->age,
            'gender' => $request->gender,
            // 'birthdate' => $request->birthdate,
            'address' => $request->address,
            'chiefcomplaint' => $request->chiefcomplaint,
            'diagnosis' => $request->diagnosis,
            'treatment' => $request->treatment,

        ];
        $res_updated = $this->database->getReference($this->tableName.'/'.$key)->update($updateData);
        if($res_updated )
        {
             return redirect('MainPatient')->with('status', 'Updated Successfully');
        }
        else{
            return redirect('MainPatient')->with('status', 'Record Not Modified');
        }

    }

    public function destroy($id)
        {
            $key=$id;
            $del_data=$this->database->getReference($this ->tableName.'/'.$key)->remove();
            if($del_data)
            {
                return redirect('MainPatient')->with('status', 'Deleted Successfully');
            }
            else{
                return redirect('MainPatient')->with('status', 'Record Not Modified');
            }
        }


}
