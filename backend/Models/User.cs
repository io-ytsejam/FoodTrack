using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class User
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Nickname { get; set; }
        [Required]
        public String Password { get; set; }
        [Required]
        public String Firstname { get; set; }
        [Required]
        public String Lastname { get; set; }

        public ICollection<Recipe> recipes { get; set; }
        public ICollection<Comment> comments { get; set; }
        public ICollection<User_history> user_Histories { get; set; }
        public ICollection<Rating> ratings { get; set; }
        public ICollection<User_settings> user_Settings { get; set; }
        public ICollection<User_healthRestrictions> user_HealthRestrictions { get; set; }
        public ICollection<User_preferences> user_Preferences { get; set; }
    }
}
