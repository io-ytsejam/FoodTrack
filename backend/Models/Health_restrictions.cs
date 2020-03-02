using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Health_restrictions
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Name { get; set; }

        public ICollection<User_healthRestrictions> user_HealthRestrictions { get; set; }
    }
}
