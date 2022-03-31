<?php
	namespace App\Controller;

	use App\Entity\Opinion;
	use App\Repository\OpinionRepository;
	use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
	use Symfony\Component\HttpKernel\Attribute\AsController;

	
	class OpinionController extends AbstractController{

		public function forRecipe(int $recipeId) : Array{
            $em = $this->getDoctrine()->getManager();
            $query = $em->createQuery(
            'SELECT o
                FROM App\Entity\Opinion o
                JOIN App\Entity\Recipe r
                WHERE r.id = :recipeId
                ORDER BY o.date'
            )->setParameter("recipeId", $recipeId);

			return $query->getResult();
		}
		
		public function getDetails(string $email, int $recipeId) : Opinion {
            $em = $this->getDoctrine()->getManager();
            $query = $em->createQuery(
            'SELECT o
                FROM App\Entity\Opinion o
                JOIN App\Entity\User u
                JOIN App\Entity\Recipe r
                WHERE r.id = :recipeId AND u.email = :email'
            )->setParameter("recipeId", $recipeId)
            ->setParameter("email", $email);
			
			$opinion = $query->getResult()[0];
			
			$query2 = $em->createQuery(
            'SELECT u.nickname
                FROM App\Entity\User u
                WHERE u.email = :email'
            )->setParameter("email", $email);
            
            $opinion->nickname = $query->getResult()[0];
			
			return $opinion;
		}

	}
?>
