<?php

defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';

class Supir extends REST_Controller
{
   public function __construct() 
   {
       parent::__construct();
       $this->load->model('m_supir');
   }
    
   public function index_get()
   {
       $id = $this->get('id');
       if ($id === null) {
           $supir = $this->m_supir->getSupir();
       } else {
           $supir = $this->m_supir->getSupir($id);
       }

       if ($supir) {
        $this->response([
            'status' => true,
            'message' => 'Success get supir'
            'data' => $supir
        ], REST_Controller::HTTP_OK);
       } else {
        $this->response([
            'status' => false,
            'message' => 'id not found'
        ], REST_Controller::HTTP_NOT_FOUND);
       }
   } 
}