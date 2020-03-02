using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class User_history
    {
        [Key]
        public int Id { get; set; }
        
        public User user { get; set; }
        public Recipe recipe { get; set; }
    }
}
