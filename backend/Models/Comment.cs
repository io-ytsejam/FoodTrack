using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Comment
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Content { get; set; }

        public User user { get; set; }
    }
}
