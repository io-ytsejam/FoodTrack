using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Preferences
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Category { get; set; }

        public ICollection<User_preferences> user_Preferences { get; set; }
    }
}
