<?php
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Firebase\ContactController;



// Route::pologin/{provider}/callback', 'Auth\LoginController@handleCallback'


Route::get('MainPatient', [ContactController::class, 'index']);
Route::get('signup', [ContactController::class, 'create']);
// Route::get('add-resident', [ContactController::class, 'create']);
Route::post('add-resident', [ContactController::class, 'store']);
Route::get('edit-patient/{id}', [ContactController::class,'edit' ]);
Route::put('update-resident/{id}', [ContactController::class, 'update']);
Route::get('delete-patient/{id}', [ContactController::class, 'destroy']);

Route::get('/', function () {
    return view('welcome');
});

