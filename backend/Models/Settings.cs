using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Settings
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Name { get; set; }

        public ICollection<User_settings> user_Settings { get; set; }
    }
}
