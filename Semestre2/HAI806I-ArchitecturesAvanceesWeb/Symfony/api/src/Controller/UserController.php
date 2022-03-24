<?php
namespace App\Controller;

use App\Entity\User;
use App\Repository\RecipeRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpKernel\Attribute\AsController;
use App\Entity\ApiResponse;


class UserController extends AbstractController{

    public function register(User $data) : ApiResponse{
        $em = $this->getDoctrine()->getManager();
        $em->getRepository(User::class)->add($data);

        return new ApiResponse(true);
    }

    public function connection(String $email, String $password) : User{
        $criteria = array('email' => $email, 'password' => $password);

        return  $this->getDoctrine()->getManager()->getRepository(User::class)->findOneBy($criteria);
    }
}
?>