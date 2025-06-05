"use client"

import React from 'react'
import { useState } from "react"
import Link from "next/link"
import Image from "next/image"
import { Bell, ChevronDown, ChevronLeft, ChevronRight, Play, Plus, Search } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { useRouter } from "next/navigation"
import Cookies from "js-cookie"
import { Cookie } from 'next/font/google'

function Navbar() {
    const router = useRouter();

    const handleLogout = () => {
        Cookies.remove('auth-token');
        router.push('/auth/login');
    }
    return (
        <div><header className="fixed top-0 w-full bg-gradient-to-b from-black via-black/95 to-transparent border-b border-black/95 backdrop-blur-sm z-50">
            <div className="flex justify-around py-4">
                <Link href="/" className="px-10 text-2xl font-bold text-red-600">
                    StreamFlix
                </Link>
                <nav className="flex space-x-6 pt-2">
                    <Link href='/' className="text-sm font-medium text-white/70 hover:text-white">
                        Home
                    </Link>
                    <Link href='#' className="text-sm font-medium text-white/70 hover:text-white">
                        TV Shows
                    </Link>
                    <Link href='/movies' className="text-sm font-medium text-white/70 hover:text-white">
                        Movies
                    </Link>
                    <Link href='#' className="text-sm font-medium text-white/70 hover:text-white">
                        New & Popular
                    </Link>
                    <Link href='#' className="text-sm font-medium text-white/70 hover:text-white">
                        My List
                    </Link>

                </nav>
                <div className="relative hidden md:flex items-center">
                    <Search className="absolute left-2.5 h-4 w-4 text-white/70" />
                    <Input
                        type="search"
                        placeholder="Titles, people, genres"
                        className="w-[180px] lg:w-[240px] pl-8 bg-black/20 border-white/10 text-sm focus-visible:ring-white/20"
                    />
                </div>

                <button
                    onClick={() => handleLogout()}
                    className="bg-transparent border-none p-0 font-medium text-white/70 hover:text-rose-500 transition-all duration-300 cursor-pointer"
                >
                    Logout
                    </button>
            </div>
        </header></div>
    )
}

export default Navbar