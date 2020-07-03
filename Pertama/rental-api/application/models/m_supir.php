<?php

class m_supir extends CI_Model
{
    public function getSupir($id = null)
    {
        if( $id === null ) {
        return $this->db->get('tb_supir')->result_array();
    } else {
        return $this->db->get_where('tb_supir', ['ID_SUPIR' => $id])->result_array();
    }
    }
}