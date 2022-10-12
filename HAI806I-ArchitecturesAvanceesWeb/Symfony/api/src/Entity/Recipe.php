<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\RecipeRepository;
use Doctrine\ORM\Mapping as ORM;
use App\Controller\RecipeController;
use App\Entity\User;
use App\Entity\Ingredient;
use App\Entity\Step;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

/**
	@ApiResource(
		collectionOperations={
			"get"={"method"="GET"},
			"post"={"method"="POST"},
            "recipes_routes"={"route_name"="recipe_search"}
		},
		itemOperations={
			"get"={"method"="GET"},
		}
	)
 * @ORM\Table(options={"collate"="utf8_general_ci", "charset": "UTF8"})
 * @ORM\Entity(repositoryClass=RecipeRepository::class)
 */
class Recipe
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $name;

    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\User", inversedBy="")
     * @ORM\JoinColumn(name="user_id", referencedColumnName="id")
     */
    private $user;

    /**
     * @ORM\Column(type="integer")
     */
    private $quantityPerson;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $cookingMode;

    /**
     * @ORM\Column(type="integer")
     */
    private $difficulty;
    
    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Ingredient", mappedBy="recipe", orphanRemoval=false)
     */
    private $ingredients;
    
    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Opinion", mappedBy="recipe", orphanRemoval=false)
     */
    private $opinions;
    
    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Step", mappedBy="recipe", orphanRemoval=false)
     */
    private $steps;
    
    
    public function __construct()
    {
        $this->ingredients = new ArrayCollection();
        $this->opinions = new ArrayCollection();
        $this->steps = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getQuantityPerson(): ?int
    {
        return $this->quantityPerson;
    }

    public function setQuantityPerson(int $quantityPerson): self
    {
        $this->quantityPerson = $quantityPerson;

        return $this;
    }

    public function getCookingMode(): ?string
    {
        return $this->cookingMode;
    }

    public function setCookingMode(string $cookingMode): self
    {
        $this->cookingMode = $cookingMode;

        return $this;
    }

    public function getDifficulty(): ?int
    {
        return $this->difficulty;
    }

    public function setDifficulty(int $difficulty): self
    {
        $this->difficulty = $difficulty;

        return $this;
    }
    
    public function getIngredients(): Collection
    {
        return $this->ingredients;
    }

    public function setIngredients(Collection $ingredients): self
    {
        $this->ingredients = $ingredients;

        return $this;
    }
    
    public function getOpinions(): Collection
    {
        return $this->opinions;
    }

    public function setOpinions(Collection $opinions): self
    {
        $this->opinions = $opinions;

        return $this;
    }
    
    public function getSteps(): Collection
    {
        return $this->steps;
    }

    public function setSteps(Collection $steps): self
    {
        $this->steps = $steps;

        return $this;
    }
}
