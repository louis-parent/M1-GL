<?php

    class ApiResponse{
        /**
         * @var bool
         */
        private $success;
        /**
         * @var String
         */
        private $message;

        public function __construct(boolean $success, String $data = ""){
            $this->message = $data;
            $this->success = $success;
        }
    }

?>