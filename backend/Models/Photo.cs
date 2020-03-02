using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Photo
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public String Photo_link { get; set; }

        public Recipe recipe { get; set; }
    }
}
