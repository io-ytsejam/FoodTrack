using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Ingredient
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Name { get; set; }

        public ICollection<Photo> recipe_Ingredients { get; set; }
    }
}
