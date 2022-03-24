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
                WHERE o.reciperId = :recipeId
                ORDER BY r.date'
            )->setParameter("recipeId", $recipeId);

			return $query->getResult();
		}
		
		public function getDetails(string $email, int $recipeId) : Opinion {
            $em = $this->getDoctrine()->getManager();
            $query = $em->createQuery(
            'SELECT o
                FROM App\Entity\Opinion o
                WHERE o.reciperId = :recipeId AND o.email = :email
                ORDER BY r.date'
            )->setParameter("recipeId", $recipeId)
            ->setParameter("email", $email);

			return $query->getResult()[0];
		}

	}
?>
