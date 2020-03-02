using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class Rating
    {
        [Key]
        public int Id { get; set; }
        [Column(TypeName = "decimal(1)")]
        public int Value { get; set; }
    }
}
