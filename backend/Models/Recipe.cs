using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Recipe
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Name { get; set; }
        [Required]
        public String Description { get; set; }
        [Required]
        public String Steps { get; set; }
        [Required]
        public bool ifExternal { get; set; }

        public ICollection<Rating> ratings { get; set; }
        public User user { get; set; }
        public ICollection<Photo> photos { get; set; }
        public ICollection<Recipe_ingredient> recipe_Ingredients { get; set; }
        public ICollection<User_history> user_Histories { get; set; }
    }
}
