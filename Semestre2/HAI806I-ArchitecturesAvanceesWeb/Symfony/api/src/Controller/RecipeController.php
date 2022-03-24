<?php
	namespace App\Controller;

	use App\Entity\Recipe;
	use App\Repository\RecipeRepository;
	use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
	use Symfony\Component\HttpKernel\Attribute\AsController;

	
	class RecipeController extends AbstractController{
		public function search(String $name, String $author, int $difficulty, String $ingredient, float $price) : Array{
            $em = $this->getDoctrine()->getManager();
            $query = $em->createQuery(
            'SELECT r
                FROM App\Entity\Recipe r
                WHERE r.name like :name AND r.difficulty <= :difficulty
                ORDER BY r.name'
            )->setParameter("name", "%" . $name . "%")
            ->setParameter("difficulty", $difficulty);

			return $query->getResult();
		}

	}
?>
