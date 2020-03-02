﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace backend.Models
{
    public class User_preferences
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public bool Value { get; set; }

        public User user { get; set; }
        public Preferences preferences { get; set; }
    }
}
